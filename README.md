# daily-activities-app
Simple app for displaying end editing daily activities from REST API.

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
