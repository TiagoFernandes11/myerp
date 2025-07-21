"use client";

import AuthGuard from "./security/AuthGuard";

export default function Home() {
  return (
    <AuthGuard>
      <h1>Hello world</h1>
    </AuthGuard>
  );
}
