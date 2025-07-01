"use client";

import "./page.css";

import AuthGuard from "../../security/AuthGuard";

import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import { getClientById } from "@/app/clients/service/clientService";
import FormHeader from "@/app/components/lists/FormHeader";
import EntityForm from "../../components/lists/EntityForm";
import { deleteClient, updateClient } from "../service/clientService";

export default function ClientByID() {
  const { id } = useParams();
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

  useEffect(() => {
    if (!id) {
      console.error("Not a valid id");
      return;
    }
    getClientById(id).then((client) => setClient(client));
  }, [id]);

  return (
    <AuthGuard>
      {client ? (
        <>
          <FormHeader
            entity={client}
            entityName={"clients"}
            saveFunction={updateClient}
            deleteFunction={deleteClient}
          />
          <EntityForm entity={client} setEntity={setClient} isNew={false} />
        </>
      ) : (
        <div>carregando</div>
      )}
    </AuthGuard>
  );
}
