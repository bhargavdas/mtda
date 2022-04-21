# MTDA Yocto layer

mtda-image is based on core-image-base

### Building for imx boards
1. Following bsp build instructions from https://source.codeaurora.org/external/imx/imx-manifest/tree/README?h=imx-linux-honister (Tested with `DISTRO=fsl-imx-xwayland`)
2. Once build setup is created, add this layer in `conf/bblayer.conf`.
3. Change `MTDA_MASS_STORAGE_FILE` variable in `conf/local.conf` according to storage medium if required, default is `mmcblk0`
4. Build the image
    > bitbake mtda-image

### Known issues
1. While building new image after changing the value of `MTDA_MASS_STORAGE_FILE` in `local.conf` (if already a image was alredy built), errors related to `usb-ms-use-storage` can be observed. Currenty the work around is cleaning the `usb-ms-use-storage` package with `bitbake usb-ms-use-storage` and then building the image again.
