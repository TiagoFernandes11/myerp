"use client";

import "./page.css";
import { useState } from "react";
import { create } from "../services/clientService";

export default function RegisterPage() {
  const [newClient, setNewClient] = useState({});
  const [error, setError] = useState("");

  function handleChange(field, value) {
    setNewClient((previous) => ({
      ...previous,
      [field]: value,
    }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    if (validateForm()) {
      const response = await create(newClient);

      if (response === 201) {
        alert("Customer account successfully created");
        setError("Customer account successfully created");
      }
    } else {
      alert("All fields must be valid");
      setError("All fields must be valid");
    }
  }

  function validateForm() {
    if (
      !newClient.email ||
      !newClient.password ||
      !newClient.firstName ||
      !newClient.lastName ||
      !newClient.ddd ||
      !newClient.cellphone ||
      !newClient.birthday ||
      !newClient
    ) {
      return false;
    } else {
      return true;
    }
  }

  return (
    <div className="formContainer">
      <form>
        {error && <p className="message">{error}</p>}
        <label>Email</label>
        <input
          type="text"
          onChange={(e) => handleChange("email", e.target.value)}
        />

        <label>Password</label>
        <input
          type="password"
          onChange={(e) => handleChange("password", e.target.value)}
        />

        <label>First name</label>
        <input
          type="text"
          onChange={(e) => handleChange("firstName", e.target.value)}
        />

        <label>Last name</label>
        <input
          type="text"
          onChange={(e) => handleChange("lastName", e.target.value)}
        />

        <label>DDD</label>
        <input
          type="text"
          onChange={(e) => handleChange("ddd", e.target.value)}
        />

        <label>Cellphone</label>
        <input
          type="text"
          onChange={(e) => handleChange("cellphone", e.target.value)}
        />

        <label>BirthDay</label>
        <input
          type="date"
          onChange={(e) => handleChange("birthday", e.target.value)}
        />

        <label>CPF</label>
        <input
          type="text"
          onChange={(e) => handleChange("cpf", e.target.value)}
        />

        <button onClick={handleSubmit}>Submit</button>
      </form>
    </div>
  );
}
