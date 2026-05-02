import { useState, type SubmitEvent } from "react";
import { useNavigate } from "react-router-dom";

type Role = "INNENDIENST" | "AUSSENDIENST";

function RegisterForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [passwordCheck, setPasswordCheck] = useState("");
  const [role, setRole] = useState<Role>("INNENDIENST");

  const navigate = useNavigate();

  const handleSubmit = async (e: SubmitEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (password !== passwordCheck) {
      alert("Passwörter stimmen nicht überein");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          username: username,
          password: password,
          role: role,
        }),
      });

      if (response.ok) {
        setUsername("");
        setPassword("");
        setPasswordCheck("");
        setRole("INNENDIENST");

        alert("Registrierung erfolgreich! Bitte logge dich jetzt ein.");
        navigate("/login");
      } else {
        const errorText = await response.text();
        alert(
          `FEHLER VOM BACKEND!\nStatus-Code: ${response.status}\nNachricht: ${errorText}`,
        );
      }
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <>
      <div className="max-w-md min-w-sm overflow-hidden bg-gray-100 rounded-xl border">
        <div className="flex flex-col justify-center items-center px-6 py-6">
          <img
            src="logo.png"
            alt="MiniCRM Logo"
            className="w-20 h-auto object-cover"
          />
          <h3 className="text-xl mb-4 text-center">Registrieren</h3>
          <form onSubmit={handleSubmit} className="min-w-70">
            <div className="flex flex-col gap-1">
              <label className="pb-2">Benutzername:</label>
              <input
                type="text"
                placeholder="Benutzername"
                value={username}
                required
                onChange={(e) => setUsername(e.target.value)}
                className="border p-2 rounded-lg"
              />
            </div>

            <div className="flex flex-col gap-1 mt-3">
              <label className="pb-2">Passwort:</label>
              <input
                type="password"
                placeholder="Passwort"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="border p-2 rounded-lg"
              />
            </div>

            <div className="flex flex-col gap-1 mt-3">
              <label className="pb-2">Passwort wiederholen:</label>
              <input
                type="password"
                placeholder="Passwort wiederholen"
                required
                value={passwordCheck}
                onChange={(e) => setPasswordCheck(e.target.value)}
                className="border p-2 rounded-lg"
              />
            </div>

            <div className="flex flex-col mt-3">
              <label className="pb-2">Rolle wählen:</label>
              <label className="flex items-center gap-2">
                <input
                  type="radio"
                  name="role"
                  value="INNENDIENST"
                  checked={role === "INNENDIENST"}
                  onChange={(e) => setRole(e.target.value as Role)}
                />
                Innendienst
              </label>
              <label className="flex items-center gap-2">
                <input
                  type="radio"
                  name="role"
                  value="AUSSENDIENST"
                  checked={role === "AUSSENDIENST"}
                  onChange={(e) => setRole(e.target.value as Role)}
                />
                Außendienst
              </label>
            </div>

            <button
              type="submit"
              className="bg-emerald-500 text-white px-6 py-2 rounded-lg mt-4 cursor-pointer active:scale-95 transition-transform"
            >
              Registrieren
            </button>
          </form>
        </div>
      </div>
    </>
  );
}

export default RegisterForm;
