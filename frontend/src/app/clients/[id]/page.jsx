"use client";

import "./page.css";

import AuthGuard from "../../security/AuthGuard";

import ClientForm from "../components/client/clientForm";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import { getClientById } from "@/app/clients/service/clientService";
import FormHeader from "../components/client/formHeader";

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
      <FormHeader client={client} isNew={false} />
      <ClientForm
        client={client ? client : null}
        setClient={setClient}
        isNew={false}
      />
    </AuthGuard>
  );
}
