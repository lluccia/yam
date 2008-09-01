; Script generated by the HM NIS Edit Script Wizard.

; HM NIS Edit Wizard helper defines
!define PRODUCT_NAME "Yam!"
;!define PRODUCT_VERSION "0.1b"
!define PRODUCT_UNINST_KEY "Software\Microsoft\Windows\CurrentVersion\Uninstall\${PRODUCT_NAME}"
!define PRODUCT_UNINST_ROOT_KEY "HKLM"

; MUI 1.67 compatible ------
!include "MUI.nsh"

; MUI Settings
!define MUI_ABORTWARNING
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install.ico"
!define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\modern-uninstall.ico"

; Language Selection Dialog Settings
!define MUI_LANGDLL_REGISTRY_ROOT "${PRODUCT_UNINST_ROOT_KEY}"
!define MUI_LANGDLL_REGISTRY_KEY "${PRODUCT_UNINST_KEY}"
!define MUI_LANGDLL_REGISTRY_VALUENAME "NSIS:Language"

; Welcome page
!insertmacro MUI_PAGE_WELCOME
; Directory page
!insertmacro MUI_PAGE_DIRECTORY
; Instfiles page
!insertmacro MUI_PAGE_INSTFILES
; Finish page
!insertmacro MUI_PAGE_FINISH

; Uninstaller pages
!insertmacro MUI_UNPAGE_INSTFILES

; Language files
!insertmacro MUI_LANGUAGE "PortugueseBR"
!insertmacro MUI_LANGUAGE "English"

; MUI end ------

Name "${PRODUCT_NAME} ${PRODUCT_VERSION}"
OutFile "${OUT_DIR}\YamSetup.exe"
InstallDir "$PROGRAMFILES\Yam!"
ShowInstDetails show
ShowUnInstDetails show

Function .onInit
  !insertmacro MUI_LANGDLL_DISPLAY
FunctionEnd

Section "MainSection" SEC01
  
  SetOverwrite try
  
  SetOutPath "$INSTDIR"
  File "..\dist\README.TXT"
  File "..\dist\Yam.jar"
  File "Yam.ico"
  
  SetOutPath "$INSTDIR\images"
  File "..\dist\images\btnJogar.png"
  File "..\dist\images\cartela.png"
  File "..\dist\images\dados.png"
  
  SetOutPath "$INSTDIR\lib"
  File "..\dist\lib\AbsoluteLayout.jar"
  File "..\dist\lib\beansbinding-1.2.1.jar"
  
  SetOutPath "$INSTDIR\sounds"
  File "..\dist\sounds\d1.au"
  File "..\dist\sounds\d2.au"
  File "..\dist\sounds\d3.au"
  File "..\dist\sounds\d4.au"
  File "..\dist\sounds\d5.au"
  File "..\dist\sounds\marca.au"
  File "..\dist\sounds\risca.au"

  SetOutPath "$INSTDIR"
  CreateDirectory "$STARTMENU\Yam!"
  CreateShortCut "$STARTMENU\Yam!\Yam!.lnk" "$INSTDIR\Yam.jar" "" "$INSTDIR\Yam.ico"
  CreateShortCut "$DESKTOP\Yam!.lnk" "$INSTDIR\Yam.jar" "" "$INSTDIR\Yam.ico"
SectionEnd

Section -AdditionalIcons
  CreateShortCut "$STARTMENU\Yam!\Uninstall.lnk" "$INSTDIR\uninst.exe"
SectionEnd

Section -Post
  WriteUninstaller "$INSTDIR\uninst.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayName" "$(^Name)"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "UninstallString" "$INSTDIR\uninst.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayVersion" "${PRODUCT_VERSION}"
SectionEnd


Function un.onUninstSuccess
  HideWindow
  MessageBox MB_ICONINFORMATION|MB_OK "$(^Name) was successfully removed from your computer."
FunctionEnd

Function un.onInit
!insertmacro MUI_UNGETLANGUAGE
  MessageBox MB_ICONQUESTION|MB_YESNO|MB_DEFBUTTON2 "Are you sure you want to completely remove $(^Name) and all of its components?" IDYES +2
  Abort
FunctionEnd

Section Uninstall
  Delete "$INSTDIR\uninst.exe"
  Delete "$INSTDIR\Yam.jar"
  Delete "$INSTDIR\sounds\risca.au"
  Delete "$INSTDIR\sounds\marca.au"
  Delete "$INSTDIR\sounds\d5.au"
  Delete "$INSTDIR\sounds\d4.au"
  Delete "$INSTDIR\sounds\d3.au"
  Delete "$INSTDIR\sounds\d2.au"
  Delete "$INSTDIR\sounds\d1.au"
  Delete "$INSTDIR\README.TXT"
  Delete "$INSTDIR\lib\AbsoluteLayout.jar"
  Delete "$INSTDIR\lib\beansbinding-1.2.1.jar"
  Delete "$INSTDIR\images\dados.png"
  Delete "$INSTDIR\images\cartela.png"
  Delete "$INSTDIR\images\btnJogar.png"
  Delete "$INSTDIR\Yam.ico"

  Delete "$STARTMENU\Yam!\Uninstall.lnk"
  Delete "$STARTMENU\Yam!\Yam!.lnk"
  Delete "$DESKTOP\Yam!.lnk"

  RMDir "$STARTMENU\Yam!"
  RMDir "$STARTMENU\Yam!"
  RMDir "$INSTDIR\sounds"
  RMDir "$INSTDIR\lib"
  RMDir "$INSTDIR\images"
  RMDir "$INSTDIR"

  DeleteRegKey ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}"
  SetAutoClose true
SectionEnd