# Rest API for a Contacts Page
 
This API is created with Spring boot 3.1.1

## Features
- Models with proper relationships
- Controllers/Models etc with proper separation of concerns
- JWT Authentication
- Swagger documentation

## Routes List:

### Contacts

| Method   | URI                           | Action                                                 |
|----------|-------------------------------|--------------------------------------------------------|
| `POST`   | `api/contacts`                | `Contactsapi\rest\ContactController@create`            |
| `GET`    | `api/contacts`                | `Contactsapi\rest\ContactController@findAll`           |
| `GET`    | `/api/contacts/{id}`          | `Contactsapi\rest\ContactController@findById`          |
| `GET`    | `/api/contacts/{name}`        | `Contactsapi\rest\ContactController@findByName`        | 
| `GET`    | `/api/contacts/{lastname}`    | `Contactsapi\rest\ContactController@findByLastname`    |
| `GET`    | `/api/contacts/{ocupation}`   | `Contactsapi\rest\ContactController@findByOcupation`   |
| `GET`    | `/api/contacts/{phonenumber}` | `Contactsapi\rest\ContactController@findByPhoneNumber` |
| `DELETE` | `api/contacts/{id}`           | `Contactsapi\rest\ContactController@deleteById`        |
| `PUT`    | `api/contacts/{id}`           | `Contactsapi\rest\ContactController@update`            |

### Users

| Method   | URI                 | Action                                          |
|----------|---------------------|-------------------------------------------------|
| `POST`   | `api/auth/register` | `Contactsapi\rest\ContactController@create`     |
| `POST`   | `api/contact/login` | `Contactsapi\rest\ContactController@findAll`    |

