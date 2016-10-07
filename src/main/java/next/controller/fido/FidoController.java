package next.controller.fido;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import next.controller.UserSessionUtils;
import next.model.Assertion;
import next.model.Fido;
import next.model.Result;
import next.model.User;
import next.repository.FidoRepository;
import next.repository.UserRepository;
import next.service.FidoService;

@RestController
@RequestMapping("/fido")
public class FidoController {
	private Logger log = LoggerFactory.getLogger(FidoController.class);
	
	@Autowired
	private FidoRepository fidoRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FidoService fidoService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public List<Fido> list() throws Exception {
		return fidoRepository.findAll();
	}
	
	@RequestMapping(value="/makeCredential", method = RequestMethod.POST)
	public Result addFido(@Valid Fido fido) throws Exception {
		log.debug("makeCredential\r\ncredId : {},\r\npublicKey : {}", fido.getCredId(), fido.getPublicKey() );
		
		// cred id 중복체크 해야함
		Fido savedFido = fidoRepository.save(fido);
		if (savedFido == null) {
			return Result.fail("Fido 정보 저장에 실패했습니다.");
		}
		return Result.ok();
	}
	
	@RequestMapping(value="/verifyAssertion", method = RequestMethod.POST)
	public Result verifyFido(@Valid Assertion assertion, HttpSession session) throws Exception {
		log.debug("verifyAssertion( credId : {} )", assertion.getCredId() );
		
		User user = userRepository.findByUserId(assertion.getCredId());
	    Fido fido = fidoRepository.findByCredId(assertion.getCredId());
		if (user == null) {
			return Result.fail("등록된 사용자가 아닙니다.");
		}
		if (fido == null) {
			return Result.fail("FIDO 저장소에 등록되지 않은 사용자 입니다.");
		}
		
		if (!fidoService.validateSignature(fido.getPublicKey(), assertion.getClientData(), assertion.getAuthnrData(), assertion.getSignature(), assertion.getChallenge())){
			return Result.fail("FIDO 인증처리에 실패하였습니다. ");
		}
		
		session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
		return Result.ok();
	}
	
	
}
