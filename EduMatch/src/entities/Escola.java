package entities;

import enums.TipoEscola;

public class Escola {

        private String nome;
        private TipoEscola tipo;
        private String cnpj;

        public Escola(String nome, TipoEscola tipo, String cnpj) {
                this.nome = nome;
                this.tipo = tipo;
                this.cnpj = cnpj;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public TipoEscola getTipo() {
                return tipo;
        }

        public void setTipo(TipoEscola tipo) {
                this.tipo = tipo;
        }

        public String getCnpj() {
                return cnpj;
        }

        public void setCnpj(String cnpj) {
                this.cnpj = cnpj;
        }

        @Override
        public String toString() {
                return "Escola{" +
                        "nome='" + nome + '\'' +
                        ", tipo=" + tipo +
                        ", cnpj='" + cnpj + '\'' +
                        '}';
        }

        public void cadastrarAluno (String nomeAluno){
            System.out.println("Realizado o cadastro do aluno: " + nomeAluno);
        }
}
