import "./index.css";

export default function Client({ client, onSelect, isSelected }) {
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
