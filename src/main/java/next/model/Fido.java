package next.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Fido {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Size(min = 3, max = 12)
	private String credId;						// 난중에 외래키로 변경
	@Size(min = 1, max = 5000)
	private String publicKey;

	public Fido() {
	}

	public Fido(String credId, String publicKey) {
		this.credId = credId;
		this.publicKey = publicKey;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCredId() {
		return credId;
	}

	public void setCredId(String credId) {
		this.credId = credId;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credId == null) ? 0 : credId.hashCode());
		result = prime * result + ((publicKey == null) ? 0 : publicKey.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Fido other = (Fido) obj;
		return toString().equals(other.toString());
	}

	@Override
	public String toString() {
		return "Fido [credId=" + credId + ", publicKey=" + publicKey + "]";
	}
}
