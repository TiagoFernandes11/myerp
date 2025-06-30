import "./index.css";

export default function Client({ client, isSelected, ...props }) {
  return (
    <tr
      id="client"
      {...props}
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
