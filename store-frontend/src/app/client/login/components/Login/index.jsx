"use client";

import { useState } from "react";
import axios from "axios";
import { useRouter } from "next/navigation";

import "./index.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const router = useRouter();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/api/login", {
        username: email,
        password: password,
      });

      if (response.status === 200) {
        localStorage.setItem("token", response.data.token);
        router.push(localStorage.getItem("redirectAfterLogin") || "/");
      }
    } catch (err) {
      setError("Email ou senha inválidos");
    }
  };

  return (
    <div className={"loginContainer"}>
      <h2>Login</h2>
      <form onSubmit={handleSubmit} className={"loginForm"}>
        <div>
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Senha:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <p className={"error"}>{error}</p>}
        <button type="submit">Entrar</button>
      </form>
    </div>
  );
}
