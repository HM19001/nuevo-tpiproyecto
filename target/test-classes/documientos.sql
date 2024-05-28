
CREATE TABLE public.atributo (
    id_atributo bigint NOT NULL,
    id_tipo_documento integer,
    id_tipo_atributo integer,
    nombre character varying(155) NOT NULL,
    nombre_pantalla character varying(155),
    indicaciones_pantalla text,
    obligatorio boolean DEFAULT false
);


--
-- TOC entry 204 (class 1259 OID 17622)
-- Name: atributo_id_atributo_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.atributo_id_atributo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 204
-- Name: atributo_id_atributo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.atributo_id_atributo_seq OWNED BY public.atributo.id_atributo;


--
-- TOC entry 207 (class 1259 OID 17652)
-- Name: documento; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.documento (
    id_documento bigint NOT NULL,
    nombre character varying,
    referencia_externa text,
    ubicacion_fisica text,
    url text,
    fecha_creacion timestamp with time zone DEFAULT now(),
    creado_por character varying(155),
    comentarios text
);


--
-- TOC entry 206 (class 1259 OID 17650)
-- Name: documento_id_documento_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.documento_id_documento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 206
-- Name: documento_id_documento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.documento_id_documento_seq OWNED BY public.documento.id_documento;


--
-- TOC entry 211 (class 1259 OID 17683)
-- Name: metadato; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.metadato (
    id_metadata bigint NOT NULL,
    id_documento bigint,
    id_atributo bigint,
    valor text,
    fecha_creacion timestamp with time zone DEFAULT now(),
    comentarios text
);


--
-- TOC entry 210 (class 1259 OID 17681)
-- Name: metadato_id_metadata_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.metadato_id_metadata_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 210
-- Name: metadato_id_metadata_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.metadato_id_metadata_seq OWNED BY public.metadato.id_metadata;


--
-- TOC entry 209 (class 1259 OID 17664)
-- Name: taxonomia; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.taxonomia (
    id_taxonomia bigint NOT NULL,
    id_documento bigint,
    id_tipo_documento integer,
    fecha_creacion timestamp with time zone DEFAULT now()
);


--
-- TOC entry 208 (class 1259 OID 17662)
-- Name: taxonomia_id_taxonomia_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.taxonomia_id_taxonomia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 208
-- Name: taxonomia_id_taxonomia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.taxonomia_id_taxonomia_seq OWNED BY public.taxonomia.id_taxonomia;


--
-- TOC entry 201 (class 1259 OID 17593)
-- Name: tipo_atributo; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tipo_atributo (
    id_tipo_atributo integer NOT NULL,
    nombre character varying(155),
    observaciones text,
    expresion_regular text,
    nombre_screen character varying(155),
    indicaciones_screen text
);


--
-- TOC entry 200 (class 1259 OID 17591)
-- Name: tipo_atributo_id_tipo_documento_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tipo_atributo_id_tipo_documento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 200
-- Name: tipo_atributo_id_tipo_documento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tipo_atributo_id_tipo_documento_seq OWNED BY public.tipo_atributo.id_tipo_atributo;


--
-- TOC entry 203 (class 1259 OID 17613)
-- Name: tipo_documento; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tipo_documento (
    id_tipo_documento integer NOT NULL,
    nombre character varying,
    activo boolean,
    observaciones text
);


--
-- TOC entry 202 (class 1259 OID 17611)
-- Name: tipo_documento_id_tipo_documento_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tipo_documento_id_tipo_documento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 202
-- Name: tipo_documento_id_tipo_documento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tipo_documento_id_tipo_documento_seq OWNED BY public.tipo_documento.id_tipo_documento;


--
-- TOC entry 2889 (class 2604 OID 17627)
-- Name: atributo id_atributo; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.atributo ALTER COLUMN id_atributo SET DEFAULT nextval('public.atributo_id_atributo_seq'::regclass);


--
-- TOC entry 2891 (class 2604 OID 17655)
-- Name: documento id_documento; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.documento ALTER COLUMN id_documento SET DEFAULT nextval('public.documento_id_documento_seq'::regclass);


--
-- TOC entry 2895 (class 2604 OID 17686)
-- Name: metadato id_metadata; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.metadato ALTER COLUMN id_metadata SET DEFAULT nextval('public.metadato_id_metadata_seq'::regclass);


--
-- TOC entry 2893 (class 2604 OID 17667)
-- Name: taxonomia id_taxonomia; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.taxonomia ALTER COLUMN id_taxonomia SET DEFAULT nextval('public.taxonomia_id_taxonomia_seq'::regclass);


--
-- TOC entry 2887 (class 2604 OID 17596)
-- Name: tipo_atributo id_tipo_atributo; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_atributo ALTER COLUMN id_tipo_atributo SET DEFAULT nextval('public.tipo_atributo_id_tipo_documento_seq'::regclass);


--
-- TOC entry 2888 (class 2604 OID 17616)
-- Name: tipo_documento id_tipo_documento; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_documento ALTER COLUMN id_tipo_documento SET DEFAULT nextval('public.tipo_documento_id_tipo_documento_seq'::regclass);


--
-- TOC entry 3051 (class 0 OID 17624)
-- Dependencies: 205
-- Data for Name: atributo; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3053 (class 0 OID 17652)
-- Dependencies: 207
-- Data for Name: documento; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3057 (class 0 OID 17683)
-- Dependencies: 211
-- Data for Name: metadato; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3055 (class 0 OID 17664)
-- Dependencies: 209
-- Data for Name: taxonomia; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 3047 (class 0 OID 17593)
-- Dependencies: 201
-- Data for Name: tipo_atributo; Type: TABLE DATA; Schema: public; Owner: -
--

--INSERT INTO public.tipo_atributo (id_tipo_atributo, nombre, observaciones, expresion_regular, nombre_screen, indicaciones_screen) VALUES (1, 'DUI', NULL, '^[0-9]{8}-\d$', 'Numero DUI', 'Escriba el numero de DUI incluyendo guiones');
--INSERT INTO public.tipo_atributo (id_tipo_atributo, nombre, observaciones, expresion_regular, nombre_screen, indicaciones_screen) VALUES (2, 'nombre_archivo', NULL, '.', 'Nombre del archivo', 'Nombre original del archivo');
INSERT INTO public.tipo_atributo ( nombre, observaciones, expresion_regular, nombre_screen, indicaciones_screen) VALUES ( 'DUI', NULL, '^[0-9]{8}-\d$', 'Numero DUI', 'Escriba el numero de DUI incluyendo guiones');
INSERT INTO public.tipo_atributo ( nombre, observaciones, expresion_regular, nombre_screen, indicaciones_screen) VALUES ( 'nombre_archivo', NULL, '.', 'Nombre del archivo', 'Nombre original del archivo');


--
-- TOC entry 3049 (class 0 OID 17613)
-- Dependencies: 203
-- Data for Name: tipo_documento; Type: TABLE DATA; Schema: public; Owner: -
--

--INSERT INTO public.tipo_documento (id_tipo_documento, nombre, activo, observaciones) VALUES (1, 'factura_cliente', true, 'Contiene la imagen de una factura emitida por la organizacion a un cliente por algun bien o servicio');
--INSERT INTO public.tipo_documento (id_tipo_documento, nombre, activo, observaciones) VALUES (2, 'acta_defuncion', true, 'Imagen de un acta de defuncion presentada por un cliente para traslados o tramites legales');
INSERT INTO public.tipo_documento ( nombre, activo, observaciones) VALUES ( 'factura_cliente', true, 'Contiene la imagen de una factura emitida por la organizacion a un cliente por algun bien o servicio');
INSERT INTO public.tipo_documento (nombre, activo, observaciones) VALUES ('acta_defuncion', true, 'Imagen de un acta de defuncion presentada por un cliente para traslados o tramites legales');

--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 204
-- Name: atributo_id_atributo_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.atributo_id_atributo_seq', 1, false);


--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 206
-- Name: documento_id_documento_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.documento_id_documento_seq', 1, false);


--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 210
-- Name: metadato_id_metadata_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.metadato_id_metadata_seq', 1, false);


--
-- TOC entry 3072 (class 0 OID 0)
-- Dependencies: 208
-- Name: taxonomia_id_taxonomia_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.taxonomia_id_taxonomia_seq', 1, false);


--
-- TOC entry 3073 (class 0 OID 0)
-- Dependencies: 200
-- Name: tipo_atributo_id_tipo_documento_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.tipo_atributo_id_tipo_documento_seq', 2, true);


--
-- TOC entry 3074 (class 0 OID 0)
-- Dependencies: 202
-- Name: tipo_documento_id_tipo_documento_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.tipo_documento_id_tipo_documento_seq', 2, true);


--
-- TOC entry 2903 (class 2606 OID 17633)
-- Name: atributo pk_atributo; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.atributo
    ADD CONSTRAINT pk_atributo PRIMARY KEY (id_atributo);


--
-- TOC entry 2905 (class 2606 OID 17661)
-- Name: documento pk_documento; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.documento
    ADD CONSTRAINT pk_documento PRIMARY KEY (id_documento);


--
-- TOC entry 2909 (class 2606 OID 17692)
-- Name: metadato pk_metadata; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.metadato
    ADD CONSTRAINT pk_metadata PRIMARY KEY (id_metadata);


--
-- TOC entry 2907 (class 2606 OID 17670)
-- Name: taxonomia pk_taxonomia; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.taxonomia
    ADD CONSTRAINT pk_taxonomia PRIMARY KEY (id_taxonomia);


--
-- TOC entry 2898 (class 2606 OID 17601)
-- Name: tipo_atributo pk_tipo_atributo; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_atributo
    ADD CONSTRAINT pk_tipo_atributo PRIMARY KEY (id_tipo_atributo);


--
-- TOC entry 2900 (class 2606 OID 17621)
-- Name: tipo_documento pk_tipo_documento; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tipo_documento
    ADD CONSTRAINT pk_tipo_documento PRIMARY KEY (id_tipo_documento);


--
-- TOC entry 2901 (class 1259 OID 17644)
-- Name: fki_fk_atributo_tipo_atributo; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX fki_fk_atributo_tipo_atributo ON public.atributo USING btree (id_tipo_atributo);


--
-- TOC entry 2910 (class 2606 OID 17639)
-- Name: atributo fk_atributo_tipo_atributo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.atributo
    ADD CONSTRAINT fk_atributo_tipo_atributo FOREIGN KEY (id_tipo_atributo) REFERENCES public.tipo_atributo(id_tipo_atributo) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;


--
-- TOC entry 2911 (class 2606 OID 17645)
-- Name: atributo fk_atributo_tipo_documento; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.atributo
    ADD CONSTRAINT fk_atributo_tipo_documento FOREIGN KEY (id_tipo_documento) REFERENCES public.tipo_documento(id_tipo_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2915 (class 2606 OID 17698)
-- Name: metadato fk_metadata_atributo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.metadato
    ADD CONSTRAINT fk_metadata_atributo FOREIGN KEY (id_atributo) REFERENCES public.atributo(id_atributo) ON UPDATE RESTRICT ON DELETE CASCADE;


--
-- TOC entry 2914 (class 2606 OID 17693)
-- Name: metadato fk_metadata_documento; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.metadato
    ADD CONSTRAINT fk_metadata_documento FOREIGN KEY (id_documento) REFERENCES public.documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2913 (class 2606 OID 17676)
-- Name: taxonomia fk_taxonomia_documento; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.taxonomia
    ADD CONSTRAINT fk_taxonomia_documento FOREIGN KEY (id_documento) REFERENCES public.documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- TOC entry 2912 (class 2606 OID 17671)
-- Name: taxonomia fk_taxonomia_tipo_documento; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.taxonomia
    ADD CONSTRAINT fk_taxonomia_tipo_documento FOREIGN KEY (id_tipo_documento) REFERENCES public.tipo_documento(id_tipo_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


-- Completed on 2024-03-12 02:15:24 UTC

--
-- PostgreSQL database dump complete
--