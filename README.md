# reservation-system

## Assumptions:
1. Designed this from the hotel manager's point of view.
2. Hotel manager manages the tables and the bookings happened based on the flow

## APIs:
1. GET /api/tables: list of tables along with their status
2. GET /api/tables/{id}: get a table with id
3. GET /api/tables/capacity?capacity={capacity}: get tables with specific capacity
4. POST /api/tables: create a table and returns the id
5. PUT /api/tables/{id}/status: update the table status to available or full or outofservice
6. PUT /api/tables/stats: get the stats of all tables
      
![image](https://github.com/NikhilR03/hotel-table-reservation/assets/137254886/f47d9fc2-7ce9-4bad-bc96-5294c9f87213)

Note: Deployed in azure spring run application=> [/api/<url>](https://restaurant1.wonderfulplant-d36ccb66.eastus.azurecontainerapps.io)

## Frontend:
1. Gets updated list of tables.
2. Availability perncentages are shown
3. Provision to add tables and manage tables is given
4. Option to filter the tables by capcity is provided

Note: Deployed in azure static web app=> [angular-frontend](https://victorious-water-09600150f.3.azurestaticapps.net)


Note:
1. this is just a prototype
2. this can expand, and further features/enhancements can be done upon request with ease.
3. Proper OOD principles are followed.


