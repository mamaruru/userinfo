# userinfo
# curl http://localhost:8080/v3/api-docs.yaml -o openapi.yaml
 
SELECT 
    s.name AS schema_name,
    t.name AS table_name,
    ep.value AS table_description
FROM 
    sys.extended_properties ep
    INNER JOIN sys.tables t ON ep.major_id = t.object_id AND ep.minor_id = 0
    INNER JOIN sys.schemas s ON t.schema_id = s.schema_id
WHERE 
    ep.name = 'MS_Description'
ORDER BY 
    s.name, t.name;

SELECT 
    s.name AS schema_name,
    t.name AS table_name,
    c.name AS column_name,
    ep.value AS column_description
FROM 
    sys.extended_properties ep
    INNER JOIN sys.tables t ON ep.major_id = t.object_id
    INNER JOIN sys.columns c ON ep.major_id = c.object_id AND ep.minor_id = c.column_id
    INNER JOIN sys.schemas s ON t.schema_id = s.schema_id
WHERE 
    ep.name = 'MS_Description'
ORDER BY 
    s.name, t.name, c.column_id;
