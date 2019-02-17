package beans;

public class UserBean {

	private Long id;
	private String login;
	private String senha;
	private String nome;
	private String fone;

	public UserBean() {
	}

	public UserBean(Long id, String login, String senha) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public UserBean(Long id, String login, String senha, String nome, String fone) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.fone = fone;
	}

	public UserBean(String login, String senha, String nome, String fone) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.fone = fone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}
}
