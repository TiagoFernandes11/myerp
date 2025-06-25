import axios from "axios";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";

export default function AuthGuard({ children }) {
  const [authorized, setAuthorized] = useState(false);
  const router = useRouter();

  useEffect(() => {
    localStorage.setItem("redirectAfterLogin", window.location.pathname);

    async function getToken() {
      return await axios.get("http://localhost:8080/api/token/validate", {
        headers: {
          Authorization: localStorage.getItem("token"),
        },
      });
    }

    if (getToken() == 200) {
      setAuthorized(true);
    } else {
      router.push("/login");
    }
  }, []);

  if (!authorized) {
    return null;
  }

  return <div style={{ width: "100%", height: "100%" }}>{children}</div>;
}
