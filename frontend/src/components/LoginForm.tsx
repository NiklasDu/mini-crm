import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function LoginForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  const navigate = useNavigate();
  const { login } = useAuth();

  const handleSubmit = async (e: React.SubmitEvent<HTMLFormElement>) => {
    e.preventDefault();
    setErrorMsg("");

    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
      });

      if (response.ok) {
        const data = await response.json();
        login(data.token);
        navigate("/");
      } else {
        setErrorMsg("Benutzername oder Passwort falsch.");
      }
    } catch (error) {
      setErrorMsg("Server nicht erreichbar.");
    }
  };

  return (
    <>
      <div className="max-w-md overflow-hidden bg-gray-100 rounded-xl border">
        <div className="flex flex-col justify-center items-center px-8 py-6">
          <img
            src="logo.png"
            alt="Logo Mini CRM"
            className="w-20 h-auto object-cover"
          />
          <h3 className="text-xl mb-4 text-center">Anmelden bei Mini-CRM</h3>
          <form
            onSubmit={handleSubmit}
            className="max-w-sm mx-auto flex justify-center items-start flex-col"
          >
            <label className="pb-2">Benutzername:</label>
            <input
              type="text"
              placeholder="Benutzername"
              value={username}
              required
              onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                setUsername(e.target.value)
              }
              className="border p-2 rounded-lg"
            />
            <label className="pb-2">Passwort:</label>
            <input
              type="password"
              placeholder="Passwort"
              value={password}
              required
              onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                setPassword(e.target.value)
              }
              className="border p-2 rounded-lg"
            />
            {errorMsg && (
              <div className="text-red-500 text-sm mt-2 font-semibold text-center w-full">
                {errorMsg}
              </div>
            )}
            <button
              type="submit"
              className="bg-emerald-500 text-white px-6 py-2 rounded-lg mt-2 "
            >
              Einloggen
            </button>
          </form>
          <div className="flex flex-col mt-4">
            <p>Noch kein Konto? </p>
            <button
              onClick={() => navigate("/register")}
              className="underline text-green-600"
            >
              Registrieren
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

export default LoginForm;
