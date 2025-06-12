"use client";

import "./page.css";
import Header from "./components/header";
import ClientList from "./components/clientList";

export default function ClientsPage() {
  return (
    <div id="content">
      <Header />
      <ClientList />
    </div>
  );
}
