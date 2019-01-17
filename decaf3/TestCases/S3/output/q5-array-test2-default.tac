VTABLE(_Main) {
    <empty>
    Main
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T0 = 4
    parm _T0
    _T1 =  call _Alloc
    _T2 = VTBL <_Main>
    *(_T1 + 0) = _T2
    return _T1
}

FUNCTION(main) {
memo ''
main:
    _T4 = "pa1"
    _T5 = 2
    _T6 = 0
    _T7 = (_T5 < _T6)
    if (_T7 == 0) branch _L10
    _T8 = "Decaf runtime error: The length of the created array should not be less than 0.\n"
    parm _T8
    call _PrintString
    call _Halt
_L10:
    _T9 = 4
    _T10 = (_T9 * _T5)
    _T11 = (_T9 + _T10)
    parm _T11
    _T12 =  call _Alloc
    *(_T12 + 0) = _T5
    _T13 = 0
    _T12 = (_T12 + _T11)
_L11:
    _T11 = (_T11 - _T9)
    _T14 = (_T13 < _T11)
    if (_T14 == 0) branch _L12
    _T12 = (_T12 - _T9)
    *(_T12 + 0) = _T4
    branch _L11
_L12:
    _T3 = _T12
    _T16 = 0
    _T17 = "pa2"
    _T18 = *(_T3 - 4)
    _T19 = (_T16 < _T18)
    if (_T19 == 0) branch _L13
    _T21 = 0
    _T22 = (_T16 < _T21)
    if (_T22 != 0) branch _L13
    _T23 = 4
    _T24 = (_T16 * _T23)
    _T25 = (_T3 + _T24)
    _T26 = *(_T25 + 0)
    _T20 = _T26
    branch _L14
_L13:
    _T20 = _T17
_L14:
    _T15 = _T20
    _T28 = 2
    _T29 = "pa3"
    _T30 = *(_T3 - 4)
    _T31 = (_T28 < _T30)
    if (_T31 == 0) branch _L15
    _T33 = 0
    _T34 = (_T28 < _T33)
    if (_T34 != 0) branch _L15
    _T35 = 4
    _T36 = (_T28 * _T35)
    _T37 = (_T3 + _T36)
    _T38 = *(_T37 + 0)
    _T32 = _T38
    branch _L16
_L15:
    _T32 = _T29
_L16:
    _T27 = _T32
    parm _T15
    call _PrintString
    parm _T27
    call _PrintString
}

