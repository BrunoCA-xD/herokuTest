package beans;

public class ProductBean {

	private Long id;
	private String nome;
	private Double quantidade;
	private Double valor;

	public ProductBean() {
		// TODO Auto-generated constructor stub
	}

	public ProductBean(Long id, String nome, Double quantidade, Double valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public ProductBean(String nome) {
		super();
		this.nome = nome;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
