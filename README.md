# daily-activities-app
Simple app for displaying and editing daily activities from REST API.

Starting database: `json-server --watch db.json --host <address> --port 3000`

address - IP address of the machine, on which json server is started (easy to check with *ifconfig* or *ipconfig*).

Example queries (here address is 192.168.2.110):

`http://192.168.2.110:3000/days`

`http://192.168.2.110:3000/activities/1`

```json
{
  "days": [
    {
      "id": 1,
      "date": "2020-01-01",
      "activities": [
        {
          "id": 1,
          "name": "Angielski"
        }
      ]
    },
    {
      "id": 2,
      "date": "2020-01-02",
      "activities": [
        "Yoga"
      ]
    },
    {
      "id": 3,
      "date": "2020-01-03",
      "activities": [
        "Angielski",
        "Yoga"
      ]
    },
    {
      "id": 4,
      "date": "2020-01-04",
      "activities": []
    },
    {
      "id": 5,
      "date": "2020-01-05",
      "activities": [
        "Angielski"
      ]
    }
  ],
  "activities": [
    {
      "id": 1,
      "name": "Angielski",
      "fields": [
        {
          "field_name": "Ile czasu?",
          "field_value": "2 godziny"
        },
        {
          "field_name": "Jak wrażenia?",
          "field_value": "Super!"
        }
      ]
    }
  ]
}
```
