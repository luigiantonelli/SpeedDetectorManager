-- SCHEMA --
CREATE SCHEMA public AUTHORIZATION postgres;
-- TABLES --
CREATE TABLE public.person (
   fiscal_code varchar(255) NOT NULL,
   address varchar(255) NULL,
   "name" varchar(255) NULL,
   surname varchar(255) NULL,
   CONSTRAINT person_pkey PRIMARY KEY (fiscal_code)
);

CREATE TABLE public.speed_cameras (
      id int8 NOT NULL,
      region varchar(255) NULL,
      road_type int4 NULL,
      CONSTRAINT speed_cameras_pkey PRIMARY KEY (id)
);

CREATE TABLE public.car (
    license_plate varchar(255) NOT NULL,
    car_type int4 NULL,
    fuel_type int4 NULL,
    registration_date timestamp NULL,
    "owner" varchar(255) NULL,
    CONSTRAINT car_pkey PRIMARY KEY (license_plate),
    CONSTRAINT fkdhxie66a98kp1hx204blval4 FOREIGN KEY ("owner") REFERENCES public.person(fiscal_code)
);

CREATE TABLE public.users (
      username varchar(255) NOT NULL,
      activation_code varchar(255) NULL,
      active bool NULL,
      "name" varchar(255) NULL,
      password_hash varchar(255) NULL,
      password_salt varchar(255) NULL,
      region varchar(255) NULL,
      "role" int4 NULL,
      surname varchar(255) NULL,
      creator_id varchar(255) NULL,
      CONSTRAINT users_pkey PRIMARY KEY (username),
      CONSTRAINT fktgicgmqj2jc0o3m6y3lyuue7k FOREIGN KEY (creator_id) REFERENCES public.users(username)
);

CREATE TABLE public.fines (
      id int8 NOT NULL,
      amount int4 NULL,
      "date" timestamp NULL,
      pdf_link bytea NULL,
      points int4 NULL,
      license_plate varchar(255) NULL,
      code_receiver varchar(255) NULL,
      speed_camera_id int8 NULL,
      manager_code varchar(255) NULL,
      CONSTRAINT fines_pkey PRIMARY KEY (id),
      CONSTRAINT fk4bg45sc7n90etq534s52vutep FOREIGN KEY (code_receiver) REFERENCES public.person(fiscal_code),
      CONSTRAINT fk535quhtyv5agfndu6q1a414c0 FOREIGN KEY (speed_camera_id) REFERENCES public.speed_cameras(id),
      CONSTRAINT fkkvjqjovnd3hno76ophqt6r7mi FOREIGN KEY (manager_code) REFERENCES public.users(username),
      CONSTRAINT fkmnwlcsbabjsirn6ukefr6v3qw FOREIGN KEY (license_plate) REFERENCES public.car(license_plate)
);
-- INSERT --

-- PERSON
INSERT INTO public.person (fiscal_code, address, "name", surname) VALUES('MRYWLM80A01H501H', 'via lemanidalnaso', 'Mario', 'Rossi');
INSERT INTO public.person (fiscal_code, address, "name", surname) VALUES('YTWRDG67T25H501R', 'via falsa', 'Franca', 'Bianchi');
INSERT INTO public.person (fiscal_code, address, "name", surname) VALUES('JSGST56U812H501R', 'via dei gelsi', 'Piero', 'Pieri');
INSERT INTO public.person (fiscal_code, address, "name", surname) VALUES('UWGSB78H80H501H', 'via lemanidalnaso', 'Maria', 'Mastrangeli');
-- CAR
INSERT INTO public.car (license_plate, car_type, fuel_type, registration_date, "owner") VALUES('GB786KM', 0, 1, now(), 'MRYWLM80A01H501H');
INSERT INTO public.car (license_plate, car_type, fuel_type, registration_date, "owner") VALUES('FC679LM', 4, 5, now(), 'YTWRDG67T25H501R');
INSERT INTO public.car (license_plate, car_type, fuel_type, registration_date, "owner") VALUES('GK986KM', 6, 3, now(), 'JSGST56U812H501R');
-- SC
INSERT INTO public.speed_cameras (id, region, road_type) VALUES(1, 'Lazio', 1);
INSERT INTO public.speed_cameras (id, region, road_type) VALUES(2, 'Lombardia', 0);
INSERT INTO public.speed_cameras (id, region, road_type) VALUES(3, 'Campania', 2);
INSERT INTO public.speed_cameras (id, region, road_type) VALUES(4, 'Piemonte', 1);