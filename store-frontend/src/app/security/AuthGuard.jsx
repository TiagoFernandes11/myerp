"use client";

import axios from "axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function AuthGuard({ children }) {
  const router = useRouter();
  const [authorized, setAuthorized] = useState(false);

  useEffect(() => {
    const token = localStorage.getItem("token");

    localStorage.setItem("redirectAfterLogin", window.location.pathname);
    if (!token) {
      router.push("/client/login");
      return;
    }
    axios
      .get("http://localhost:8090/api/token/validate", {
        headers: {
          Authorization: token,
        },
      })
      .then((response) => {
        if (response.status === 200) {
          setAuthorized(true);
        } else {
          router.push("/client/login");
        }
      })
      .catch((error) => {
        console.error("Invalid token", error);
        router.push("/client/login");
      });
  }, []);

  if (!authorized) {
    return null;
  }

  return <div style={{ width: "100%", height: "100%" }}>{children}</div>;
}
