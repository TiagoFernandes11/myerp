import "./index.css";
import Client from "../client";
import { getClients } from "@/app/clients/service/clientService";
import { useEffect, useState } from "react";

export default function ClientList({
  selectedClient,
  setSelectedClient,
  searchFilter,
  searchValue,
}) {
  const [clientList, setClientList] = useState([]);

  useEffect(() => {
    async function fetchClients(filter, value) {
      const clients = await getClients(filter, value);
      setClientList(clients);
    }

    fetchClients(searchFilter, searchValue);
  }, [searchFilter, searchValue]);

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
