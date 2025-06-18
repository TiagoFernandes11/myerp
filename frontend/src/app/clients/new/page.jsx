"use client";

import { useState } from "react";
import "./page.css";
import AuthGuard from "../../security/AuthGuard";
import ClientForm from "../components/client/clientForm";
import FormHeader from "../components/client/formHeader";

export default function New() {
  const [client, setClient] = useState({});

  return (
    <AuthGuard>
      <FormHeader client={client} isNew={true} />
      <ClientForm client={client} setClient={setClient} isNew={true} />
    </AuthGuard>
  );
}
