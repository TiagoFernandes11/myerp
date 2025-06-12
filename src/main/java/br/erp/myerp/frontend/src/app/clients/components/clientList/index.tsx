import "./index.css";
import Client from "../client";

const client = {
  id: 10,
  firstName: "Teste",
  lastName: "Teste",
  email: "teste@email.com",
  ddd: "11",
  cellPhone: "999999999",
  birthDay: new Date(),
  cpf: "123456789098",
};

export default function ClientList() {
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
          <Client {...client} />
        </tbody>
      </table>
    </div>
  );
}
