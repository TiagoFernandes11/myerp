'use client'; // IMPORTANTE: permite usar eventos e estado no client

import axios from 'axios';
import { useState } from 'react';

export default function LoginPage() {
  const [errors, setErrors] = useState<string[]>([]);

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault(); // impede reload

    const formData = new FormData(event.currentTarget);
    const username = formData.get("username") as string;
    const password = formData.get("password") as string;

    // Limpa erros anteriores
    setErrors([]);

    if (username && password) {
      try {
        const response = await axios.post("http://localhost:8080/api/login", {
          username,
          password,
        });

        console.log("Resposta da API:", response.data);
        localStorage.setItem("token", response.data.token);

      } catch (err: any) {
        const apiErrors = err.response?.data?.errors;
        if (Array.isArray(apiErrors)) {
          setErrors(apiErrors);
        } else if (typeof apiErrors === 'string') {
          setErrors([apiErrors]);
        } else {
          setErrors(['Erro ao fazer login.']);
        }
      }
    } else {
      setErrors(["Username and password are required"]);
    }
  }

  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <div className="bg-gray-900 pt-20 pb-20 pr-10 pl-10">
        <h1 className="mb-2 text-white">MyERP</h1>

        {errors.length > 0 && (
          <div className="mb-2 bg-red-900 p-3 rounded-md text-white">
            <ul>
              {errors.map((msg, index) => (
                <li key={index}>• {msg}</li>
              ))}
            </ul>
          </div>
        )}

        <form className="flex flex-col" method='POST' onSubmit={handleSubmit}>
          <label className="mb-2 text-white">Username</label>
          <input className="mb-4" type="text" name="username" id="username" />
          <label className="mb-2 text-white">Password</label>
          <input className="mb-4" type="password" name="password" id="password" />
          <input className="bg-blue-900 mt-5 text-white cursor-pointer" type="submit" value="Login" />
        </form>
      </div>
    </div>
  );
}
