package Controllers;

import Models.Bay;
import Models.Client;
import Models.Model;
import Views.ClientView;
import Views.ProfessionalView;

import java.util.ArrayList;

public class ClientController extends Controller{

    private ArrayList<Client> clients = new ArrayList<>();
    private Client model;
    private ClientView view;

    public ClientController(Client model, ClientView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    public Client save(String name, String cell_phone) {
        Client client = new Client(this.clients.size(), name, cell_phone);
        this.clients.add(client);

        return client;
    }
    @Override
    public Client get(int id) {
        int index = this.getById(id);

        return this.getByIndex(index);
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.clients.size(); i++) {
            Model model = this.clients.get(i);
            if (model.getCodigo() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Client getByIndex(int index) {
        return this.clients.get(index);
    }

    public int getByCell_phone(String cell) {
        for (int i = 0; i < this.clients.size(); i++) {
            Client client = (Client) this.getByIndex(i);
            if (client.getCell_phone().equalsIgnoreCase(cell)) {
                return i;
            }
        }

        return -1;
    }

    public void delete(int id) {
        int index = this.getById(id);
        this.clients.remove(index);
    }

    public void initData() {
        this.save("Fabricio", "999888777");
        this.save("Raul", "999888666");
        this.save("Roberto", "999888555");
    }
}
