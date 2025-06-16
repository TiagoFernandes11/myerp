"use client";

import "./page.css";
import Header from "./components/client/header";
import ClientList from "./components/client/clientList";
import AuthGuard from "../security/AuthGuard";

export default function ClientsPage() {
  return (
    <AuthGuard>
      <Header />
      <ClientList />
    </AuthGuard>
  );
}
