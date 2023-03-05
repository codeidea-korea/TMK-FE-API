db.createUser(
    {
        user: "tourm_user",
        pwd: "code0809_",
        "roles" : [
            {
                "role" : "userAdminAnyDatabase",
                "db" : "admin"
            },
            {
                "role" : "dbAdminAnyDatabase",
                "db" : "admin"
            },
            {
                "role" : "readWriteAnyDatabase",
                "db" : "admin"
            }
        ],
    }
);
