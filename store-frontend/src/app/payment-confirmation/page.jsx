"use client";

import AuthGuard from "../security/AuthGuard";
import "./page.css";
import { confirmOrder } from "./service/PaymentConfirmationService";

export default function PaymentConfirmationPage() {
  return (
    <AuthGuard>
      <button onClick={confirmOrder}>Confirm payment</button>
    </AuthGuard>
  );
}
