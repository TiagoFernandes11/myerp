import { useEffect, useState } from "react";
import Header from "../formHeader";
import "./index.css";

export default function ClientForm({ client, setClient, isNew }) {
  const idIndication = (
    <div className="form-row">
      <label htmlFor="client-id">Id: </label>
      <h4 id="client-id">{client?.id}</h4>
    </div>
  );

  function handleChange(fieldName, value) {
    setClient((prev) => ({
      ...prev,
      [fieldName]: value,
    }));
  }

  const content = (
    <div>
      <div className="content">
        <div className="form">
          {isNew || idIndication}
          <div className="form-row">
            <label htmlFor="first-name">First name:</label>
            <input
              id="firstName"
              type="text"
              value={client?.firstName}
              onChange={(e) => handleChange("firstName", e.target.value)}
            />
            <label htmlFor="lastName">Last name: </label>
            <input
              type="text"
              value={client?.lastName}
              onChange={(e) => handleChange("lastName", e.target.value)}
            />
          </div>
          <div className="form-row">
            <label htmlFor="email">Email: </label>
            <input
              type="text"
              value={client?.email}
              onChange={(e) => handleChange("email", e.target.value)}
            />
            <label htmlFor="birthday">Birthday: </label>
            <input
              type="date"
              value={client?.birthday}
              onChange={(e) => handleChange("birthday", e.target.value)}
            />
          </div>
          <div className="form-row">
            <label htmlFor="ddd">DDD: </label>
            <input
              type="text"
              value={client?.ddd}
              onChange={(e) => handleChange("ddd", e.target.value)}
            />
            <label htmlFor="cellphone">Cell phone: </label>
            <input
              htmlFor="cellphone"
              value={client?.cellphone}
              onChange={(e) => handleChange("cellphone", e.target.value)}
            />
          </div>
          <div className="form-row">
            <label htmlFor="cpf">CPF: </label>
            <input
              type="text"
              value={client?.cpf}
              onChange={(e) => handleChange("cpf", e.target.value)}
            />
          </div>
        </div>
      </div>
    </div>
  );

  return (
    <div className="page">{client ? content : <h1>Client not founded</h1>}</div>
  );
}
