import Header from "../header";
import "./index.css";

export default function Form({ client }) {
  return (
    <div className="page">
      <Header />
      <div className="content">
        <div className="form">
          <div className="form-row">
            <label htmlFor="client-id">Id: </label>
            <h4 id="client-id">{client?.id}</h4>
          </div>
          <div className="form-row">
            <label htmlFor="first-name">First name:</label>
            <input id="firstName" type="text" value={client?.firstName} />
            <label htmlFor="lastName">Last name: </label>
            <input type="text" value={client?.lastName} />
          </div>
          <div className="form-row">
            <label htmlFor="email">Email: </label>
            <input type="text" value={client?.email} />
            <label htmlFor="birthday">Birthday: </label>
            <input type="date" value={client?.birthday} />
          </div>
          <div className="form-row">
            <label htmlFor="ddd">DDD: </label>
            <input type="text" value={client?.ddd} />
            <label htmlFor="cellphone">Cell phone: </label>
            <input htmlFor="cellphone" value={client?.cellphone}></input>
          </div>
          <div className="form-row">
            <label htmlFor="cpf">CPF: </label>
            <input type="text" value={client?.cpf} />
          </div>
        </div>
      </div>
    </div>
  );
}
