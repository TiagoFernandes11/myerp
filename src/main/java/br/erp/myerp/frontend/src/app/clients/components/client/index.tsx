import "./index.css";

interface client {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  ddd: string;
  cellPhone: string;
  birthDay: Date;
  cpf: string;
}

export default function Client(client: client) {
  return (
    <div id="client">
      <tr>
        <td>{client.id}</td>
        <td>{client.firstName}</td>
        <td>{client.lastName}</td>
        <td>{client.email}</td>
        <td>{client.cpf}</td>
      </tr>
    </div>
  );
}
