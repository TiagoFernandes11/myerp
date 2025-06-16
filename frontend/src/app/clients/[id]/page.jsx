"use client";

import "./page.css";

import AuthGuard from "../../security/AuthGuard";

import Form from "./components/form";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import { getClientById } from "@/app/clients/service/clientService";
import Header from "./components/header";

export default function ClientByID() {
  const { id } = useParams();
  const [client, setClient] = useState(null);

  useEffect(() => {
    if (!id) {
      console.error("Not a valid id");
      return;
    }
    getClientById(id).then((client) => setClient(client));
  }, [id]);

  return (
    <AuthGuard>
      <Form client={client} />
    </AuthGuard>
  );
}
