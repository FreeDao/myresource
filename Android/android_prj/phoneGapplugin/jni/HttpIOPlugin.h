#ifndef __HTTP_IO_PLUGIN_H__
#define __HTTP_IO_PLUGIN_H__


#include "NavPlugins.h"

#ifdef __cplusplus
extern "C" {
#endif

/*
HRESULT openIOPlugin_Http(IOPLUGIN* pIOPlugin);
HRESULT openIOPlugin_Transcode(IOPLUGIN* pIOPlugin);

// Added by Zack.
//
// This is for supporting HTTP Live Streaming.
// We hook the original HTTP IO plugin.
// You can still get the original HTTP IO plugin by openIOPlugin_Http()
//
HRESULT openIOPluginProxy_Http(IOPLUGIN* pIOPlugin);
*/

#ifdef __cplusplus
}
#endif

#endif /*__HTTP_IO_PLUGIN_H__*/
