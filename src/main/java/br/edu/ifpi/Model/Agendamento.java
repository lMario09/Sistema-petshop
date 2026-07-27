package br.edu.ifpi.Model;

public class Agendamento {
    private Cliente cliente;
    private Animal animal;
    private Servico servico;
    private String data;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void decidirServico(String tipoServico) {
        if (tipoServico == null) {
            throw new IllegalArgumentException("Informe o tipo de serviço.");
        }

        switch (tipoServico.toLowerCase()) {
            case "banho":
                this.servico = new Banho("Banho");
                break;
            case "tosa":
                this.servico = new Tosa("Tosa");
                break;
            default:
                throw new IllegalArgumentException("Serviço inválido. Escolha banho ou tosa.");
        }
    }

    public void agendarServico() {
        if (servico == null) {
            throw new IllegalStateException("Nenhum serviço foi escolhido.");
        }

        System.out.println("===== Agendamento =====");
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Animal: " + animal.getNome());
        System.out.println("Serviço: " + servico.getNome());
        System.out.println("Preço: R$ " + servico.getPreco());
        System.out.println("Data: " + data);
    
    }
}
