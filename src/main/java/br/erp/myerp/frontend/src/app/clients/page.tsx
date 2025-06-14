"use client";

import "./page.css";
import Header from "./components/client/header";
import ClientList from "./components/client/clientList";

export default function ClientsPage() {
  return (
    <div id="content">
      <Header />
      <ClientList />
    </div>
  );
}
