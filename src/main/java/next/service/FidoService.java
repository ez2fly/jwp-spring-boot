package next.service;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FidoService {
	private static final Logger log = LoggerFactory.getLogger(FidoService.class);
	
	private Decoder decoder = Base64.getUrlDecoder();
	
	public FidoService() {
		
	}
	
	public boolean validateSignature(String publicKey, String clientData, String authnrData, String signature, String challenge) {
		log.debug("validateSignature()");
		
		try {
			byte[] c = decoder.decode(clientData);
			byte[] a = decoder.decode(authnrData);
			byte[] s = decoder.decode(signature);
			
			// Make sure the challenge in the client data matches the expected challenge
			String cc = new String(c);
			log.debug("cc : {}", cc);
			JSONObject json = new JSONObject(cc);
			String decodedChallenge = json.getString("challenge");
			log.debug("decoded challenge : {}", decodedChallenge);
			if (!challenge.equals(decodedChallenge)) {
				log.debug("Fail : Challenge is different ( org : {}, decoded : {}", challenge, decodedChallenge);
				return false;
			}
			
			// Hash data with sha-256
			MessageDigest hash = MessageDigest.getInstance("SHA-256");
			hash.update(c);
			byte[] h = hash.digest();
			
			// Create data buffer to verify signature over
			byte[] b = new byte[a.length + h.length];
			System.arraycopy(a, 0, b, 0, a.length);
			System.arraycopy(h, 0, b, a.length, h.length);
			
			// Load public key
			json = new JSONObject(publicKey);
			byte[] modulesBytes = decoder.decode(json.getString("n"));
			byte[] exponentBytes = decoder.decode(json.getString("e"));
			BigInteger modules = new BigInteger(1, modulesBytes);
			BigInteger exponent = new BigInteger(1, exponentBytes);
			RSAPublicKeySpec rsaPubKey = new RSAPublicKeySpec(modules, exponent);
			KeyFactory fact = KeyFactory.getInstance("RSA");
			PublicKey pubKey = fact.generatePublic(rsaPubKey);
			
			// Verify signature is correct for authnrData + hash
			Signature signatureB = Signature.getInstance("SHA256withRSA");
			signatureB.initVerify(pubKey);
			signatureB.update(b);
			return signatureB.verify(s);
		}catch(Exception ex) {
			log.debug("Fail(Exception) : \r\n{}", ex.getMessage());
		}
		return false;
	}
}
