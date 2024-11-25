Supported APi:

MEMBERS:


Add new member:
Endpoint: /api/members
Method: Post
EXAMPLE:
{
  "name": "Mitchel Joy",
  "address": "123 Easy Street",
  "email": "mitchel@example.com",
  "phoneNumber": "123-555-0198",
  "startDate": "2023-04-12",
  "duration": 24
}


Search for members:

Endpoint: /api/members/search?*QUERY*=*VALUE*
Method: Get
Supported Queries;

name
phone number (FORMATTED XXX-XXX-XXXX)
start-date (FORMATTED YYYY-MM-DD)
email




TOURNAMENTS:


Add new tournament:
Endpoint: /api/tournaments
Method: Post

EXAMPLE:
{
  "startDate": "2024-06-10",
  "endDate": "2024-06-12",
  "location": "St Johns Golf Club",
  "entryFee": 75.0,
  "cashPrize": 1000.0
}



Search for tournament:

Endpoint: /api/tournaments/search?*QUERY*=*VALUE*
Method: Get
Supported Queries;

Location
start-date (FORMATTED YYYY-MM-DD)
end-date (FORMATTED YYYY-MM-DD)

Add Member to tournament:
Endpoint: /api/tournaments/{tournamentId}/members/{memberId}
Method: Post

Get members in tournament:
Endpoint:/api/tournaments/{tournamentId}/members
Method: Get


DOCKER SUPPORT:

To start:

Build Docker Containers using:
docker-compose build  
docker-compose up    

The api will be available at http://localhost:8080










