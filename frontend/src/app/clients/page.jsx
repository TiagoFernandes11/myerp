"use client";

import "./page.css";
import GeneralHeader from "./components/client/generalHeader";
import ClientList from "./components/client/clientList";
import AuthGuard from "../security/AuthGuard";
import { useState } from "react";

export default function ClientsPage() {
  const [selectedClient, setSelectedClient] = useState();
  return (
    <AuthGuard>
      <GeneralHeader selectedClient={selectedClient} />
      <ClientList
        selectedClient={selectedClient}
        setSelectedClient={setSelectedClient}
      />
    </AuthGuard>
  );
}
