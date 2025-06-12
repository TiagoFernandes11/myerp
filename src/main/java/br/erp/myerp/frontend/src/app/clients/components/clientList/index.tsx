import "./index.css";
import Client from "../client";
import { useState } from "react";

const clientList = [
  {
    id: 10,
    firstName: "Teste",
    lastName: "Teste",
    email: "teste@email.com",
    ddd: "11",
    cellPhone: "999999999",
    birthDay: new Date(),
    cpf: "123456789098",
  },
  {
    id: 11,
    firstName: "Teste",
    lastName: "Teste",
    email: "teste@email.com",
    ddd: "11",
    cellPhone: "999999999",
    birthDay: new Date(),
    cpf: "123456789098",
  },
];

export default function ClientList() {
  const [selectedClient, setSelectedClient] = useState(clientList[0]);

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
