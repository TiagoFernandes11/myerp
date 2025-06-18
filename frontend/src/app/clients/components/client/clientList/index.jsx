import "./index.css";
import Client from "../client";
import { getClients } from "@/app/clients/service/clientService";

const clientList = await getClients();

export default function ClientList({ selectedClient, setSelectedClient }) {

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>id</th>
            <th>first name</th>
            <th>last name</th>
            <th>email</th>
            <th>cpf</th>
          </tr>
        </thead>
        <tbody>
          {clientList.map((client) => {
            return (
              <Client
                key={client.id}
                client={client}
                onSelect={setSelectedClient}
                isSelected={selectedClient?.id === client.id}
              />
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
