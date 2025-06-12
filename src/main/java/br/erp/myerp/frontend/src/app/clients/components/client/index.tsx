import "./index.css";

interface Client {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  ddd: string;
  cellPhone: string;
  birthDay: Date;
  cpf: string;
}

interface ClientProps {
  client: Client;
  onSelect: (client: Client) => void;
  isSelected: boolean;
}

export default function Client({ client, onSelect, isSelected }: ClientProps) {
  return (
    <tr
      id="client"
      onClick={() => {
        onSelect(client);
      }}
      style={{
        backgroundColor: isSelected ? "red" : "green",
        cursor: "pointer",
      }}
    >
      <td>{client.id}</td>
      <td>{client.firstName}</td>
      <td>{client.lastName}</td>
      <td>{client.email}</td>
      <td>{client.cpf}</td>
    </tr>
  );
}
