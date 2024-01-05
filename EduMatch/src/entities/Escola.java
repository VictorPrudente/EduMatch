package entities;

public class Escola {

        private String nome;
        private int tipo;
        private String cnpj;

        public Escola(String nome, int tipo, String cnpj) {
                this.nome = nome;
                this.tipo = tipo;
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

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public int getTipo() {
                return tipo;
        }

        public void setTipo(int tipo) {
                this.tipo = tipo;
        }

        public String getCnpj() {
                return cnpj;
        }

        public void setCnpj(String cnpj) {
                this.cnpj = cnpj;
        }

        public void cadastrarAluno (String nomeAluno){
            System.out.println("Realizado o cadastro do aluno: " + nomeAluno);
        }
}
