CREATE TABLE public.coffee_types (
                                     id integer NOT NULL,
                                     espresso_id integer NOT NULL,
                                     water_count integer NOT NULL,
                                     name character varying NOT NULL
);


ALTER TABLE public.coffee_types OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 32934)
-- Name: coffee_types_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.coffee_types_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.coffee_types_id_seq OWNER TO postgres;

--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 215
-- Name: coffee_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.coffee_types_id_seq OWNED BY public.coffee_types.id;


--
-- TOC entry 217 (class 1259 OID 32946)
-- Name: coffee_types_milk; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.coffee_types_milk (
                                          coffee_id integer NOT NULL,
                                          milk_id integer NOT NULL
);


ALTER TABLE public.coffee_types_milk OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 32928)
-- Name: espresso_recipes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.espresso_recipes (
                                         id integer NOT NULL,
                                         watercount integer NOT NULL,
                                         coffeecount integer NOT NULL
);


ALTER TABLE public.espresso_recipes OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 32927)
-- Name: espresso_recipes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.espresso_recipes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.espresso_recipes_id_seq OWNER TO postgres;

--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 213
-- Name: espresso_recipes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.espresso_recipes_id_seq OWNED BY public.espresso_recipes.id;


--
-- TOC entry 210 (class 1259 OID 32912)
-- Name: ingredients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredients (
                                    id integer NOT NULL,
                                    name character varying NOT NULL,
                                    count integer NOT NULL,
                                    max_count integer
);


ALTER TABLE public.ingredients OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 32911)
-- Name: ingredients_id_seq1; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ingredients_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ingredients_id_seq1 OWNER TO postgres;

--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 209
-- Name: ingredients_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ingredients_id_seq1 OWNED BY public.ingredients.id;


--
-- TOC entry 212 (class 1259 OID 32921)
-- Name: milk; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.milk (
                             id integer NOT NULL,
                             count integer NOT NULL,
                             whipped boolean NOT NULL
);


ALTER TABLE public.milk OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 32920)
-- Name: milk_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.milk_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.milk_id_seq OWNER TO postgres;

--
-- TOC entry 3357 (class 0 OID 0)
-- Dependencies: 211
-- Name: milk_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.milk_id_seq OWNED BY public.milk.id;


--
-- TOC entry 3186 (class 2604 OID 32938)
-- Name: coffee_types id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_types ALTER COLUMN id SET DEFAULT nextval('public.coffee_types_id_seq'::regclass);


--
-- TOC entry 3185 (class 2604 OID 32931)
-- Name: espresso_recipes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.espresso_recipes ALTER COLUMN id SET DEFAULT nextval('public.espresso_recipes_id_seq'::regclass);


--
-- TOC entry 3183 (class 2604 OID 32915)
-- Name: ingredients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients ALTER COLUMN id SET DEFAULT nextval('public.ingredients_id_seq1'::regclass);


--
-- TOC entry 3184 (class 2604 OID 32924)
-- Name: milk id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.milk ALTER COLUMN id SET DEFAULT nextval('public.milk_id_seq'::regclass);


--
-- TOC entry 3347 (class 0 OID 32935)
-- Dependencies: 216
-- Data for Name: coffee_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.coffee_types (id, espresso_id, water_count, name) VALUES (1, 1, 0, 'Эспрессо');
INSERT INTO public.coffee_types (id, espresso_id, water_count, name) VALUES (2, 2, 0, 'Доппио');
INSERT INTO public.coffee_types (id, espresso_id, water_count, name) VALUES (3, 3, 0, 'Триппло');
INSERT INTO public.coffee_types (id, espresso_id, water_count, name) VALUES (4, 4, 0, 'Ристретто');
INSERT INTO public.coffee_types (id, espresso_id, water_count, name) VALUES (5, 5, 0, 'Лунго');
INSERT INTO public.coffee_types (id, espresso_id, water_count, name) VALUES (6, 1, 120, 'Американо');
INSERT INTO public.coffee_types (id, espresso_id, water_count, name) VALUES (8, 1, 0, 'Каппучино');
INSERT INTO public.coffee_types (id, espresso_id, water_count, name) VALUES (9, 1, 0, 'Латте');


--
-- TOC entry 3348 (class 0 OID 32946)
-- Dependencies: 217
-- Data for Name: coffee_types_milk; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (1, 5);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (1, 6);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (2, 5);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (2, 6);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (3, 5);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (3, 6);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (4, 5);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (4, 6);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (5, 5);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (5, 6);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (8, 3);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (8, 4);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (9, 1);
INSERT INTO public.coffee_types_milk (coffee_id, milk_id) VALUES (9, 2);


--
-- TOC entry 3345 (class 0 OID 32928)
-- Dependencies: 214
-- Data for Name: espresso_recipes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.espresso_recipes (id, watercount, coffeecount) VALUES (1, 30, 7);
INSERT INTO public.espresso_recipes (id, watercount, coffeecount) VALUES (2, 60, 14);
INSERT INTO public.espresso_recipes (id, watercount, coffeecount) VALUES (3, 90, 21);
INSERT INTO public.espresso_recipes (id, watercount, coffeecount) VALUES (4, 20, 7);
INSERT INTO public.espresso_recipes (id, watercount, coffeecount) VALUES (5, 50, 7);


--
-- TOC entry 3341 (class 0 OID 32912)
-- Dependencies: 210
-- Data for Name: ingredients; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ingredients (id, name, count, max_count) VALUES (3, 'Кофе', 34, 257);
INSERT INTO public.ingredients (id, name, count, max_count) VALUES (2, 'Молоко', 100, 265);
INSERT INTO public.ingredients (id, name, count, max_count) VALUES (1, 'Вода', 1352, 1800);


--
-- TOC entry 3343 (class 0 OID 32921)
-- Dependencies: 212
-- Data for Name: milk; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.milk (id, count, whipped) VALUES (1, 80, false);
INSERT INTO public.milk (id, count, whipped) VALUES (2, 40, true);
INSERT INTO public.milk (id, count, whipped) VALUES (3, 80, true);
INSERT INTO public.milk (id, count, whipped) VALUES (4, 40, false);
INSERT INTO public.milk (id, count, whipped) VALUES (5, 0, true);
INSERT INTO public.milk (id, count, whipped) VALUES (6, 0, false);


--
-- TOC entry 3358 (class 0 OID 0)
-- Dependencies: 215
-- Name: coffee_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.coffee_types_id_seq', 9, true);


--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 213
-- Name: espresso_recipes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.espresso_recipes_id_seq', 5, true);


--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 209
-- Name: ingredients_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ingredients_id_seq1', 3, true);


--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 211
-- Name: milk_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.milk_id_seq', 6, true);


--
-- TOC entry 3197 (class 2606 OID 32950)
-- Name: coffee_types_milk coffee_types_milk_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_types_milk
    ADD CONSTRAINT coffee_types_milk_pkey PRIMARY KEY (coffee_id, milk_id);


--
-- TOC entry 3195 (class 2606 OID 32940)
-- Name: coffee_types coffee_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_types
    ADD CONSTRAINT coffee_types_pkey PRIMARY KEY (id);


--
-- TOC entry 3192 (class 2606 OID 32933)
-- Name: espresso_recipes espresso_recipes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.espresso_recipes
    ADD CONSTRAINT espresso_recipes_pkey PRIMARY KEY (id);


--
-- TOC entry 3188 (class 2606 OID 32919)
-- Name: ingredients ingredients_pkey1; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients
    ADD CONSTRAINT ingredients_pkey1 PRIMARY KEY (id);


--
-- TOC entry 3190 (class 2606 OID 32926)
-- Name: milk milk_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.milk
    ADD CONSTRAINT milk_pkey PRIMARY KEY (id);


--
-- TOC entry 3193 (class 1259 OID 32963)
-- Name: coffee_types_name_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX coffee_types_name_uindex ON public.coffee_types USING btree (name);


--
-- TOC entry 3198 (class 2606 OID 32941)
-- Name: coffee_types coffee_types_espresso_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_types
    ADD CONSTRAINT coffee_types_espresso_id_fkey FOREIGN KEY (espresso_id) REFERENCES public.espresso_recipes(id);


--
-- TOC entry 3199 (class 2606 OID 32951)
-- Name: coffee_types_milk coffee_types_milk_coffee_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_types_milk
    ADD CONSTRAINT coffee_types_milk_coffee_id_fkey FOREIGN KEY (coffee_id) REFERENCES public.coffee_types(id);


--
-- TOC entry 3200 (class 2606 OID 32956)
-- Name: coffee_types_milk coffee_types_milk_milk_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.coffee_types_milk
    ADD CONSTRAINT coffee_types_milk_milk_id_fkey FOREIGN KEY (milk_id) REFERENCES public.milk(id);


-- Completed on 2022-05-25 17:01:10

--
-- PostgreSQL database dump complete
--