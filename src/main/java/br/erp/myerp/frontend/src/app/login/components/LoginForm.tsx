"use client";

import axios from "axios";
import { useState } from "react";
import { AxiosError } from "axios";

export default function LoginForm() {
  const [errors, setErrors] = useState<string[]>([]);

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault(); // impede reload

    const formData = new FormData(event.currentTarget);
    const username = formData.get("username") as string;
    const password = formData.get("password") as string;

    setErrors([]);

    if (username && password) {
      try {
        const response = await axios.post("http://localhost:8080/api/login", {
          username,
          password,
        });

        //todo success
        localStorage.setItem("token", response.data.token);
      } catch (err: unknown) {
        const error = err as AxiosError<{ errors: string[] | string }>;
        const apiErrors = error.response?.data?.errors;
        if (Array.isArray(apiErrors)) {
          setErrors(apiErrors);
        } else if (typeof apiErrors === "string") {
          setErrors([apiErrors]);
        } else {
          setErrors(["Username or password is incorrect"]);
        }
      }
    } else {
      setErrors(["Username and password are required"]);
    }
  }
  return (
    <div className="flex flex-col items-center justify-center h-screen w-screen">
      <div className="p-10 bg-gray-800">
        <h1 className="mb-5 text-white text-4xl">MyERP</h1>

        {errors.length > 0 && (
          <div className="mb-2 bg-red-900 p-3 rounded-md text-white">
            <ul>
              {errors.map((msg, index) => (
                <li key={index}> {msg}</li>
              ))}
            </ul>
          </div>
        )}

        <form className="flex flex-col" method="POST" onSubmit={handleSubmit}>
          <label className="mb-2 text-white">Username</label>
          <input
            className="mb-4 p-2 text-black"
            type="text"
            name="username"
            id="username"
          />
          <label className="mb-2 text-white">Password</label>
          <input
            className="mb-4 p-2 text-black"
            type="password"
            name="password"
            id="password"
          />
          <input
            className="bg-blue-900 mt-5 text-white cursor-pointer h-10"
            type="submit"
            value="Login"
          />
        </form>
      </div>
    </div>
  );
}
