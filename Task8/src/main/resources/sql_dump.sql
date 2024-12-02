CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE public."User" (
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    creation_date timestamp(6) without time zone NOT NULL
);

CREATE SEQUENCE public."User_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public."User_id_seq" OWNED BY public."User".id;

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);

CREATE TABLE public."Ticket" (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    ticket_type ticket_type NOT NULL,
    creation_date timestamp(6) without time zone NOT NULL
);

ALTER TABLE ONLY public."Ticket"
    ADD CONSTRAINT "Ticket_pkey" PRIMARY KEY (id);

CREATE SEQUENCE public."Ticket_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE public."Ticket_id_seq" OWNED BY public."Ticket".id;

ALTER TABLE ONLY public."Ticket"
    ADD CONSTRAINT "Ticket_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);

ALTER TABLE ONLY public."Ticket"
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public."User"(id);