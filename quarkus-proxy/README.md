# How to test against a proxy: use tinyproxy on podman:
podman run -d --name squid -p 3128:3128 ubuntu/squid
podman exec -it squid /bin/bash
tail -f /var/log/squid/*



