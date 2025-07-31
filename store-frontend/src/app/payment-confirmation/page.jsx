"use client";

import AuthGuard from "../security/AuthGuard";
import "./page.css";

export default function PaymentConfirmationPage() {
  return (
    <AuthGuard>
      <button onClick={console.log("Payment todo")}>Confirm payment</button>
    </AuthGuard>
  );
}
