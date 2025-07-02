"use client";

import { useState } from "react";
import "./page.css";
import AuthGuard from "../../security/AuthGuard";
import EntityForm from "../../components/lists/EntityForm";
import FormHeader from "../../components/lists/FormHeader";
import { createClient } from "../service/clientService";

export default function New() {
  const [client, setClient] = useState({
    id: "",
    firstName: "",
    lastName: "",
    email: "",
    ddd: "",
    cellphone: "",
    birthday: "",
    cpf: "",
  });

  return (
    <AuthGuard>
      <FormHeader entity={client} saveFunction={createClient} isNew={true} />
      <EntityForm entity={client} setEntity={setClient} isNew={true} />
    </AuthGuard>
  );
}
