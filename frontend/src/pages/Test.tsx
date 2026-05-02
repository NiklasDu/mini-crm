import { useState, useEffect, type SubmitEvent } from "react";

interface Note {
  id: number;
  text: string;
  createdAt: string;
}

interface Customer {
  id: number;
  name: string;
  email: string;
  company: string;
  notes?: Note[]; // ? bedeutet optional
}

function App() {
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [newName, setNewName] = useState("");
  const [newEmail, setNewEmail] = useState("");
  const [newCompany, setNewCompany] = useState("");

  const handleCreateCustomer = async (e: SubmitEvent<HTMLFormElement>) => {
    e.preventDefault();

    const response = await fetch("http://localhost:8080/api/customers", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        name: newName,
        email: newEmail,
        company: newCompany,
      }),
    });

    if (response.ok) {
      const createdCustomer = await response.json();
      setCustomers([...customers, createdCustomer]);

      setNewName("");
      setNewEmail("");
      setNewCompany("");
    } else {
      console.error("Fehler beim Erstellen des Kunden");
    }
  };

  useEffect(() => {
    fetch("http://localhost:8080/api/customers")
      .then((response) => response.json())
      .then((data) => setCustomers(data))
      .catch((error) => console.error("Fehler beim Laden der Kunden:", error));
  }, []);

  return (
    <div className="p-8 font-sans max-w-4xl mx-auto">
      <h1 className="text-3xl font-bold text-gray-800 mb-2">Mini-CRM</h1>
      <h2 className="text-xl text-gray-600 mb-6">Kundenliste</h2>

      <div className="overflow-x-auto bg-white shadow-md rounded-lg mb-10">
        <table className="min-w-full text-left text-sm whitespace-nowrap">
          <thead className="uppercase tracking-wider border-b-2 border-gray-200 bg-gray-100 text-gray-700">
            <tr>
              <th className="px-6 py-4">ID</th>
              <th className="px-6 py-4">Name</th>
              <th className="px-6 py-4">E-Mail</th>
              <th className="px-6 py-4">Firma</th>
              <th className="px-6 py-4 w-1/3">Historie: (Notizen)</th>
            </tr>
          </thead>
          <tbody>
            {customers.map((customer) => (
              <tr
                key={customer.id}
                className="border-b border-gray-100 hover:bg-gray-50 transition-colors"
              >
                <td className="px-6 py-4 text-gray-500">{customer.id}</td>
                <td className="px-6 py-4 font-medium text-gray-900">
                  {customer.name}
                </td>
                <td className="px-6 py-4 text-gray-600">{customer.email}</td>
                <td className="px-6 py-4 text-gray-600">{customer.company}</td>
                <td className="px-6 py-4 text-gray-600 whitespace-normal">
                  {customer.notes && customer.notes.length > 0 ? (
                    <ul className="list-disc pl-4 space-y-1">
                      {customer.notes.map((note) => (
                        <li key={note.id} className="text-xs">
                          <span className="text-gray-400 font-mono mr-2">
                            [
                            {new Date(note.createdAt).toLocaleTimeString(
                              "de-DE",
                            )}
                            ]
                          </span>
                          {note.text}
                        </li>
                      ))}
                    </ul>
                  ) : (
                    <span className="text-gray-400 text-xs italic">
                      Keine Notizen vorhanden
                    </span>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <h2 className="text-xl text-gray-600 mb-6">Neue Kunden anlegen</h2>
      <form
        onSubmit={handleCreateCustomer}
        className="bg-white p-6 shadow-md border border-gray-200 rounded-lg mb-8 flex gap-4 items-end flex-wrap"
      >
        <div className="flex flex-col gap-1">
          <label className="text-sm font-medium text-gray-700">Name</label>
          <input
            type="text"
            required
            value={newName}
            onChange={(e) => setNewName(e.target.value)}
            className="border border-gray-300 rounded-md p-2 focus:ring-2 focus:ring-blue-500 outline-none"
            placeholder="Max Mustermann"
          />
        </div>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-medium text-gray-700">E-Mail</label>
          <input
            type="email"
            required
            value={newEmail}
            onChange={(e) => setNewEmail(e.target.value)}
            className="border border-gray-300 rounded-md p-2 focus:ring-2 focus:ring-blue-500 outline-none"
            placeholder="max@beispiel.com"
          />
        </div>
        <div className="flex flex-col gap-1">
          <input
            type="text"
            required
            value={newCompany}
            onChange={(e) => setNewCompany(e.target.value)}
            className="border border-gray-300 rounded-md p-2 focus:ring-2 focus:ring-blue-500 outline-none"
            placeholder="Beispiel GmbH"
          />
        </div>
        <button
          type="submit"
          className="bg-blue-600 text-white px-4 py-2 rounded-md hover:bg-blue-700 transition-colors font-medium"
        >
          Kunden anlegen
        </button>
      </form>
    </div>
  );
}

export default App;
