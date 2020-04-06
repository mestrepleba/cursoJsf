package modelo;
import java.io.Serializable;
import java.util.Date;
import java.util.jar.Attributes;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Pessoa implements Serializable, Cloneable {

	private Integer codigo;
	private String nome;
        private String email;
        private Date dataNascimento;
        private TipoPessoa tipoPessoa;
        private RamoAtividade ramoAtividade;
	
	public Pessoa() {
	}
	
	public Pessoa(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
        @Id //Chave da tabela
        @GeneratedValue // Auto incremento
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
        
        @Column
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
        
        @Column(name = "tipo")
        @Enumerated(EnumType.STRING)        
        public TipoPessoa getTipoPessoa() {
            return tipoPessoa;
        }

        public void setTipoPessoa(TipoPessoa tipoPessoa) {
            this.tipoPessoa = tipoPessoa;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }        
        
        @Column(name = "dataNascimento")
        public Date getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(Date dataNascimento) {
            this.dataNascimento = dataNascimento;
        }
        
        @ManyToOne //Chave estrangeira -- Muito  para um
        @JoinColumn(name = "codigo_ramo")
        public RamoAtividade getRamoAtividade() {
            return ramoAtividade;
        }

        public void setRamoAtividade(RamoAtividade ramoAtividade) {
            this.ramoAtividade = ramoAtividade;
        }        
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
        
        public Object clone() throws CloneNotSupportedException{
            return super.clone();
        }
	
}